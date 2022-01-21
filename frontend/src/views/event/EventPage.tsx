import React, { useEffect, useMemo, useState } from 'react';
import { Flex, chakra, Button, useDisclosure } from '@chakra-ui/react';
import { LeftSideMenu } from './components/LeftSideMenu';
import { observer } from 'mobx-react-lite';
import eventsStore from '../../store/events/index';
import { TableBlock } from '../../modules/tableContent/TableBlock';

const columns_meetings = [
  {
    title: 'Id',
    key: 'id',
    dataIndex: 'id',
  },
  {
    title: 'Type',
    dataIndex: 'type',
    key: 'type',
    render: (text: string) => <a>{text}</a>,
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: 'Maximum Visitors',
    dataIndex: 'maxVisitors',
    key: 'maxVisitors',
  },
  {
    title: 'Member Count',
    key: 'memberCount',
    dataIndex: 'memberCount',
  },
  {
    title: 'Food Count',
    key: 'food',
    dataIndex: 'food',
  },
  {
    title: 'Date',
    key: 'date',
    dataIndex: 'date',
  },
  {
    title: 'Присоединиться',
    key: 'join',
    dataIndex: 'join',
    render: (text: string) => <Button>Join</Button>,
  }
];

const columns_events = [
  {
    title: 'Id',
    key: 'id',
    dataIndex: 'id',
  },
  {
    title: 'Type',
    dataIndex: 'type',
    key: 'type',
    render: (text: string) => <a>{text}</a>,
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: 'Member Count',
    key: 'memberCount',
    dataIndex: 'memberCount',
  },
  {
    title: 'Date',
    key: 'date',
    dataIndex: 'date',
  },
];

export const EventsPage = observer(() => {
  const { meetings, events, futureEvents, isMeeting, isEvents } = eventsStore;
  const { onOpen, isOpen, onClose } = useDisclosure();
  const [ data, setData ] = useState({});

  console.log(data);

  return (
    <Flex h="100vh" w="100%">
      <LeftSideMenu />
      <TableBlock setData={setData} current={isMeeting ? 'meetings' : (isEvents ? 'events' : 'futureEvents')} data={isMeeting ? meetings : (isEvents ? events : futureEvents)} columns={isMeeting ? columns_meetings : columns_events} />
    </Flex>
  )
});