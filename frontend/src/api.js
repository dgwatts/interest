// Rest calls go in here
import axios from "axios";

export function getSaved() {
	return axios.get('https://127.0.0.1/')
		.then(response => {
			return response;
		});
};

export function calculate(bands, baseAmount) {
	return axios.post('https://127.0.0.1/', {
		bands,
		baseAmount
	}).then(response => {
		return response;
	});
};
