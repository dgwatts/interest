import React, {Component} from "react";
import InterestBand from "./InterestBand";

/**
 * A component for managing sets interest rate bands
 */
class InterestBands extends Component {

	constructor(props) {
		super(props);
		this.state = {
		}
	}

	updateBand = (idx, band) => {
		this.props.bands[idx] = band;
		this.props.updateBands(this.props.bands);
	}

	render() {
		return (
			<>
				<div className={"divInterestBands"}>
					<table>
						<thead>
							<tr><td className={"heading"}>Lower Bound</td><td className={"heading"}>Upper Bound</td><td className={"heading"}>Interest Rate</td><td></td></tr>
						</thead>
						<tbody>
							{this.props.bands.map((band, idx) => {
								return <InterestBand band={band} key={idx} idx={idx} updateBand={this.updateBand} deleteBand={this.props.deleteBand}/>
							})}
						</tbody>
					</table>
					<button onClick={() => {
						this.props.bands.push({lowerBound: "", upperBound: "", interestRate:""});
						this.props.updateBands(this.props.bands);
					}}>Add another band</button>
				</div>
			</>
		);
	}
}

export default InterestBands;