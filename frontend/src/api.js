// Rest calls go in here
import axios from "axios";

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

export function persist(bands, baseAmount) {
	return axios.post('/persist', {
		bands,
		baseAmount
	})
		.then(response => {
			return response.data
		});
}

export function deleteAllSaved() {
	return axios.delete('/history')
		.then(response => {
			return response.data
		});
}