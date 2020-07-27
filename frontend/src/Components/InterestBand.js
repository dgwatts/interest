import React, {Component} from "react";

import './interest.scss';

/**
 * A set of fields encapsulating one interest rate band (eg 0 - 1000 @ 1%)
 */
class InterestBand extends Component {

	delete = () => {
		this.props.deleteBand(this.props.idx);
	}

	updateBand = (event, field) => {
		let newValue = parseInt(event.target.value);
		if(!newValue) {
			newValue = "";
		}
		this.props.updateBand(this.props.idx, {...this.props.band, [field]: newValue});
	}

	render() {
		return (
			<tr>
				<td className={"lowerBound"}><input type={"text"} value={this.props.band.lowerBound} onChange={event => this.updateBand(event, "lowerBound")}/></td>
				<td className={"upperBound"}><input type={"text"} value={this.props.band.upperBound} onChange={event => this.updateBand(event, "upperBound")}/></td>
				<td className={"interestRate"}><input type={"text"} value={this.props.band.interestRate} onChange={event => this.updateBand(event, "interestRate")}/></td>
				<td className={"interestRate"}><button onClick={this.delete}>Delete band</button></td>
			</tr>
		);
	}
}

export default InterestBand;