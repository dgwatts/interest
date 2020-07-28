import React, {Component} from "react";

/**
 *  A component for managing saved sets of interest rate information
 */
class SavedPanel extends Component {

	render() {
		return (
			<div className={"savedPanel"}>
				{this.props.saved && this.props.saved.length > 0 && <button onClick={this.props.deleteAllSaved}>Delete all saved</button>}
				<div className={"savedItems"}>
					{this.props.saved && this.props.saved.map(saved => {
						return (
							<div key={saved.id} className={"savedItem"}>
								<span className={"savedItemInfo"}>Base Amount: {saved.baseAmount}</span><br/>
								<span className={"savedItemInfo"}>Interest: {saved.totalInterest}</span>
								<div className={"savedBands"}>
									{saved && saved.bands.map(band => {
										return (
											<div key={saved.id + band.lowerBound} className={"savedBand"}>{band.lowerBound} - {band.upperBound ? band.upperBound : "unbounded"} @ {band.interestRate}%</div>
										);
									})}
								</div>

								<div  className={"useButton"} >
									<button onClick={() => this.props.selectSaved(saved.id)}>Use</button>
								</div>
							</div>
						);
					})}
				</div>
			</div>
		);
	}
}

export default SavedPanel;