import React, { useEffect, useMemo, useState } from 'react';
import { Flex, chakra, Button, TabPanel, TabList, Tabs, Tab, TabPanels, Box, useDisclosure, Text } from '@chakra-ui/react';
import * as libraryApis from '../../api/library/api';
import libraryStore from '../../store/library';
import { CustomTable } from '../../components/table/Table';
import { Column } from 'react-table';
import { Empty } from 'antd';
import { TableBlock } from '../../modules/tableContent/TableBlock';

type BooksType = {
  id: number;
  name: string;
  description: string;
  borrowerId: number;
  available: boolean;
}


// TODO: change it to mobx realization

export const LibraryPage = () => {
  const { isOpen, onOpen, onClose } = useDisclosure()
  const token = localStorage.getItem('jwt');
  const [books, setBooks] = useState<BooksType[]>([]);
  const [myBooks, setMyBooks] = useState<BooksType[]>([]);
  const [borrow, setBorrow] = useState<BooksType>();
  const [availableBooks, setAvailableBooks] = useState<BooksType[]>([]);

  function getBooks() {
    libraryApis.getAllBooksApi(token).then((res) => {
      setBooks(res);
    })
  }

  function getAvailableBooks() {
    libraryApis.getAvailableBooksApi(token).then((res) => {
      setAvailableBooks(res);
    })
  }

  function getMyBooks() {
    libraryApis.getMyBooksApi(token).then((res) => {
      setMyBooks(res);
    })
  }

  const columns = [
    {
      title: 'Id',
      key: 'id',
      dataIndex: 'id',
    },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
    },
    {
      title: 'Borrowed Id',
      key: 'borrowerId',
      dataIndex: 'borrowerId',
    },
    {
      title: 'Available',
      key: 'available',
      dataIndex: 'available',
      render: (isAvailable: boolean, row: any) => isAvailable ? (
        <Button bgColor={'deepskyblue'} onClick={() => {
          libraryStore.borrowBook(row.id);
          getBooks();
          getMyBooks();
          getAvailableBooks();
        }}>Забронировать</Button>
      ) : (
        <Text color={'red.500'}>Занято</Text>
      )
    },
  ];

  const columns1 = [
    {
      title: 'Id',
      key: 'id',
      dataIndex: 'id',
    },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
    },
    {
      title: 'Borrowed Id',
      key: 'borrowerId',
      dataIndex: 'borrowerId',
    },
    {
      title: 'Available',
      key: 'available',
      dataIndex: 'available',
      render: (isAvailable: boolean, row: any) => isAvailable ? (
        <Button bgColor={'deepskyblue'} onClick={() => {
          libraryStore.borrowBook(row.id);
          getBooks();
          getMyBooks();
          getAvailableBooks();
        }}>Забронировать</Button>
      ) : (
        <Button bgColor="red.500" onClick={() => {
          libraryStore.returnBook(row.id)
          getBooks();
          getMyBooks();
          getAvailableBooks();
        }}>Вернуть</Button>
      )
    },
  ];

  useEffect(() => getBooks(), []);

  console.log(borrow);

  const data = useMemo(() => books, [books]);
  const myBooksData = useMemo(() => myBooks, [myBooks]);
  const availableBooksData = useMemo(() => availableBooks, [availableBooks]);

  // const columns: Column<any>[] = useMemo(
  //   () => [
  //     {
  //       Header: 'id',
  //       accessor: 'id',
  //     },
  //     {
  //       Header: 'name',
  //       accessor: 'name',
  //     },
  //     {
  //       Header: 'description',
  //       accessor: 'description',
  //     },
  //     {
  //       Header: 'borrowerId',
  //       accessor: 'borrowerId',
  //     },
  //     {
  //       Header: 'available',
  //       accessor: 'available',
  //       Cell: ({ value }) => {
  //         return value ?
  //           <Button color="blue.500" onClick={(r) => console.log(r, 'button')}>Забронировать</Button> :
  //           <chakra.span color="red.500">Занято</chakra.span>;
  //       },
  //     },
  //   ],
  //   []
  // );
  return (
    <>
      <Flex h='100vh' w='100%'>
        <Tabs isLazy w='100%'>
          <TabList>
            <Tab
              _focus={{ boxShadow: 'none' }}
            >All books</Tab>
            <Tab _focus={{ boxShadow: 'none' }} onClick={() => {
              getAvailableBooks();
            }}>Available books</Tab>
            <Tab _focus={{ boxShadow: 'none' }} onClick={() => {
              getMyBooks();
            }}>My books</Tab>
          </TabList>

          <TabPanels w='100%'>
            <TabPanel>
              <TableBlock
                columns={columns}
                data={data}
              />
            </TabPanel>
            <TabPanel>
              <TableBlock
                columns={columns}
                data={availableBooksData}
              />
            </TabPanel>
            <TabPanel>
              <TableBlock
                columns={columns1}
                data={myBooksData}
              />
            </TabPanel>
          </TabPanels>
        </Tabs>
      </Flex>
    </>
  )
};