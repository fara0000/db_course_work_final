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

  @action
  borrowBook = async (id: number) => {
    const token = localStorage.getItem('jwt');
    const resp = await libraryApis.borrowBookApi(id, token);
    console.log(resp);
  }
}

export default new LibraryStore();
