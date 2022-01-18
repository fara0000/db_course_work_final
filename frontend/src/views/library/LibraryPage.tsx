import React, { useEffect, useMemo, useState } from 'react';
import { Flex, chakra, Button, TabPanel, TabList, Tabs, Tab, TabPanels } from '@chakra-ui/react';
import * as libraryApis from '../../api/library/api';
import libraryStore from '../../store/library';
import { CustomTable } from '../../components/table/Table';
import { Column } from 'react-table';

type BooksType = {
  id: number;
  name: string;
  description: string;
  borrowerId: number;
  available: boolean;
}

// TODO: change it to mobx realization

export const LibraryPage = () => {
  const [books, setBooks] = useState<BooksType[]>([]);
  const [myBooks, setMyBooks] = useState<BooksType[]>([]);
  const [availableBooks, setAvailableBooks] = useState<BooksType[]>([]);

  function getBooks() {
    libraryApis.getAllBooksApi().then((res) => {
      setBooks(res);
    })
  }

  function getAvailableBooks() {
    libraryApis.getAvailableBooksApi().then((res) => {
      setAvailableBooks(res);
    })
  }

  function getMyBooks() {
    libraryApis.getMyBooksApi().then((res) => {
      setMyBooks(res);
    })
  }

  useEffect(() => getBooks(), []);

  const data = useMemo(() => books, [books]);
  const myBooksData = useMemo(() => myBooks, [myBooks]);
  const availableBooksData = useMemo(() => availableBooks, [availableBooks]);

  const columns: Column<any>[] = useMemo(
    () => [
      {
        Header: 'id',
        accessor: 'id',
      },
      {
        Header: 'name',
        accessor: 'name',
      },
      {
        Header: 'description',
        accessor: 'description',
      },
      {
        Header: 'borrowerId',
        accessor: 'borrowerId',
      },
      {
        Header: 'available',
        accessor: 'available',
        Cell: ({ value }) => {
          return value ?
            <Button color="blue.500">Забронировать</Button> :
            <chakra.span color="red.500">Занято</chakra.span>;
        },
      },
    ],
    []
  );

  return (
    <Flex h="100vh" w="100%">
      <Tabs isLazy w="100%">
        <TabList>
          <Tab
            _focus={{ boxShadow: "none" }}
          >All books</Tab>
          <Tab _focus={{ boxShadow: "none" }} onClick={() => {
            getAvailableBooks()
          }}>Available books</Tab>
          <Tab _focus={{ boxShadow: "none" }}>My books</Tab>
        </TabList>

        <TabPanels w="100%">
          <TabPanel>
            <CustomTable columns={columns} data={data} />
          </TabPanel>
          <TabPanel>
            <CustomTable columns={columns} data={availableBooksData} />
          </TabPanel>
          <TabPanel>
            <CustomTable columns={columns} data={myBooksData} />
          </TabPanel>
        </TabPanels>
      </Tabs>
    </Flex>
  )
};