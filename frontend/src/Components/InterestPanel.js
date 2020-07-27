import React, {Component} from "react";
import SavedPanel from "./SavedPanel";
import InterestBands from "./InterestBands";
import {calculate} from "../api";

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

	updateBands = (newBands) => {
		this.setState({bands: newBands})
	}

	deleteBand = (idx) => {
		const bands = this.state.bands;
		bands.splice(idx, 1);
		this.setState({bands});
	}

	componentDidMount() {
		// TODO implement in stage 3
		// Retrieve saved values
		//getSaved().then(response => this.setState({saved: response}))
	}

	updateBaseAmount = (event) => {
		this.setState({baseAmount: event.target.value})
	}

	calculateInterest = () => {
		// Get the bands from state

		// Send them to the backend
		calculate(this.state.bands, this.state.baseAmount).then(data => {
			console.log("calculate", data);
			this.handleResponse(null);
			this.setState({interestEarned: data.totalInterest})
		})
			.catch(response => {
				this.handleResponse(response.response.data);
			});
	}

	save = () => {
		// TODO implement in stage 3
		// Persist the current values
	}

	handleResponse = (message) => {
		this.setState({errorMessage: message})
	}

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
				<button onClick={this.save}>Save</button>
				<SavedPanel updateBands={this.updateBands}/>
				{this.state.errorMessage && <div className={"errorMessage"}>{this.state.errorMessage}</div>}
			</>
		);
	}
}

export default InterestPanel;

