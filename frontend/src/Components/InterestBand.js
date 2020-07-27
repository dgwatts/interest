import React, {Component} from "react";

import './interest.scss';

/**
 * A set of fields encapsulating one interest rate band (eg 0 - 1000 @ 1%)
 */
class InterestBand extends Component {

	delete = () => {
		this.props.deleteBand(this.props.idx);
	}

	render() {
		return (
			<tr>
				<td className={"lowerBound"}>{this.props.band.lowerBound}</td>
				<td className={"upperBound"}>{this.props.band.upperBound}</td>
				<td className={"interestRate"}>{this.props.band.interestRate}</td>
				<td className={"interestRate"}><button onClick={this.delete}>Delete band</button></td>
			</tr>
		);
	}
}

export default InterestBand;