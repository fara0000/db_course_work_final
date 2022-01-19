import axios from 'axios';
import * as libraryUrls from '../urls';

export const getMyBooksApi = (token: string | null) => {
  return axios.get(libraryUrls.getMyBooksUrl, {
    headers: {
      'Authorization': `Bearer${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('getMyBooksApi error:', err))
};

export const getAllBooksApi = (token: string | null) => {
  return axios.get(libraryUrls.getAllBooksUrl, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data.books)
    .catch((err) => console.log('getAllBooksApi error:', err))
};

export const getAvailableBooksApi = (token: string | null) => {
  return axios.get(libraryUrls.getAvailableBooksUrl, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data.books)
    .catch((err) => console.log('getAvailableBooksApi error:', err))
};

export const borrowBookApi = (id: number, token: string) => {
  return axios.post(libraryUrls.getAvailableBooksUrl + `${id}/borrow`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('borrowBookApi error:', err))
};

export const returnBookApi = (id: number, token: string) => {
  return axios.post(libraryUrls.getAvailableBooksUrl + `${id}/return`, {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
    .then((res) => res.data)
    .catch((err) => console.log('returnBookApi error:', err))
};

