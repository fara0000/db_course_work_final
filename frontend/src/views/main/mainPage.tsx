import React, { FC, useEffect } from 'react';
import { Flex } from '@chakra-ui/react';
import authStore from '../../store/auth';
import synagogueStore from '../../store/synagogue';
import * as synagoguesApi from '../../api/synagogue/api';
import { observer } from 'mobx-react-lite';
import { UserType } from '../auth/types';


export const MainPage: FC = observer(() => {
  const { user } = authStore;
  const { isLoading, members, mySynagogue } = synagogueStore;

  useEffect(() => {
    synagogueStore.getMySynagogueInfo();
  }, [])

  const user1 = {...user};
  console.log(user1, 'user');
  // console.log(!isLoading && members, 'members')
  // console.log(!isLoading && mySynagogue, 'mySynagogue')

  return (
    <Flex h="100vh" w="100%" alignItems="center" justifyContent="center" fontSize="40px" color="blue.500">
      Welcome, you successfully signed in to our website!
      {mySynagogue?.tradition.description}
      {
        !isLoading && members.map((item) => <span>{item.name}</span>)
      }
    </Flex>
  )
});