import axios from 'axios';
import * as libraryUrls from '../urls';

export const getMyBooksApi = () => {
  return axios.get(libraryUrls.getMyBooksUrl)
    .then((res) => res.data)
    .catch((err) => console.log('getMyBooksApi error:', err))
};

export const getAllBooksApi = () => {
  return axios.get(libraryUrls.getAllBooksUrl)
    .then((res) => res.data.books)
    .catch((err) => console.log('getAllBooksApi error:', err))
};

export const getAvailableBooksApi = () => {
  return axios.get(libraryUrls.getAvailableBooksUrl)
    .then((res) => res.data.books)
    .catch((err) => console.log('getAvailableBooksApi error:', err))
};

export const borrowBookApi = (id: number) => {
  return axios.post(libraryUrls.getAvailableBooksUrl + `${id}/borrow`)
    .then((res) => res.data)
    .catch((err) => console.log('borrowBookApi error:', err))
};

export const returnBookApi = (id: number) => {
  return axios.post(libraryUrls.getAvailableBooksUrl + `${id}/return`)
    .then((res) => res.data)
    .catch((err) => console.log('returnBookApi error:', err))
};

