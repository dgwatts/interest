// Rest calls go in here
import axios from "axios";
import response from "./Components/InterestPanel"

export function getSaved() {
	return axios.get('/history')
		.then(response => response.data);
}

export function calculate(bands, baseAmount) {
	return axios.post('/calculate', {
		bands,
		baseAmount
	})
		.then(response => {
			return response.data
		});
}
