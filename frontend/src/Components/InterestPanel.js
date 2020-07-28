import React, {Component} from "react";
import SavedPanel from "./SavedPanel";
import InterestBands from "./InterestBands";
import {calculate, persist, getSaved, deleteAllSaved} from "../api";

/**
 * The complete UI component for the interest calculator
 */
class InterestPanel extends Component {

	constructor(props) {
		super(props);

		this.state = {
			bands: [
				{lowerBound: 0, upperBound: 1000, interestRate: 1},
				{lowerBound: 1000, upperBound: 5000, interestRate: 2},
				{lowerBound: 5000, upperBound: null, interestRate: 3}
			],
			baseAmount: 6400,
			interestEarned: 0,
			saved: []
		}
	}

	selectSaved = (id) => {
		const filtered = this.state.saved.filter((saved) => saved.id === id)
		if(filtered.length === 0) {
			this.handleResponse("No saved entried matched");
		}
		else if(filtered.length > 1) {
			this.handleResponse("Multiple saved entried matched");
		}
		else {
			this.handleResponse(null);
			this.setState({
				bands: filtered[0].bands,
				baseAmount: filtered[0].baseAmount,
				interestEarned: filtered[0].totalInterest
			});
		}
	}

	updateBands = (newBands) => {
		this.setState({bands: newBands})
	}

	deleteBand = (idx) => {
		const bands = this.state.bands;
		bands.splice(idx, 1);
		this.setState({bands});
	}

	componentDidMount() {
		// Retrieve saved values
		getSaved().then(response => this.setState({saved: response}))
	}

	updateBaseAmount = (event) => {
		this.setState({baseAmount: event.target.value})
	}

	calculateInterest = () => {
		// Send the bands to the backend
		calculate(this.state.bands, this.state.baseAmount)
			.then(data => {
				this.handleResponse(null);
				this.setState({interestEarned: data.totalInterest})
			})
			.catch(response => {
				this.handleResponse(response);
			});
	}

	saveValues = () => {
		persist(this.state.bands, this.state.baseAmount)
			.then(data => {
				this.handleResponse(null);
				this.setState({saved: data});
			})
			.catch(response => {
				this.handleResponse(response.response.data);
			});
	};

	deleteAllSaved = () => {
		deleteAllSaved()
			.then(() => this.setState({saved: []}));
	};

	handleResponse = (response) => {
		if(!response || typeof response === "string") {
			// Internal error or clear error
			this.setState({errorMessage: response});
		}
		else if(typeof response.response.data === "string") {
			// backend-handled error
			this.setState({errorMessage: response.response.data})
		}
		else {
			// Jackson-handled error
			this.setState({errorMessage: response.response.data.message})
		}
	};

	render() {
		return (
			<>
				<InterestBands bands={this.state.bands} updateBands={this.updateBands} deleteBand={this.deleteBand}/>
				<div>
					<div><span className={"label"}>Base Amount</span><input type={"text"} value={this.state.baseAmount} onChange={this.updateBaseAmount}/></div>
					<button onClick={this.calculateInterest}>Calculate Interest</button>
				</div>
				<div>
					<div><span className={"label"}>Interest Earned: </span><span>{this.state.interestEarned}</span></div>
				</div>
				<button onClick={this.saveValues}>Save</button>
				<div className={"errorMessage"}>{this.state.errorMessage && this.state.errorMessage}</div>
				<SavedPanel saved={this.state.saved} selectSaved={this.selectSaved} deleteAllSaved={this.deleteAllSaved}/>
			</>
		);
	}
}

export default InterestPanel;