import { action, makeAutoObservable, observable } from 'mobx';
import * as libraryApis from '../../api/library/api';

class LibraryStore {
  books = [];
  isLoading = false;

  constructor() {
    makeAutoObservable(this);
    this.getAllBooks()
  }

  @action
  getAllBooks = async () => {
    const token = localStorage.getItem('jwt');
    this.isLoading = true;
    this.books = await libraryApis.getAllBooksApi(token);
    this.isLoading = false;
  }
}

export default new LibraryStore();
