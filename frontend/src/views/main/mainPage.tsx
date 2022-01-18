import React, { FC, useEffect } from 'react';
import { Flex } from '@chakra-ui/react';
import authStore from '../../store/auth';
import * as synagoguesApi from '../../api/synagogue/api';
import { observer } from 'mobx-react-lite';
import { UserType } from '../auth/types';

export interface Props {
  userData: UserType;
}

export const MainPage: FC<Props> = observer(({userData}) => {
  useEffect(() => {
    synagoguesApi.getMySynagogueInfoApi();
    synagoguesApi.getMySynagogueMembersApi();
  }, []);

  console.log(userData);
  return (
    <Flex h="100vh" w="100%" alignItems="center" justifyContent="center" fontSize="40px" color="blue.500">
      Welcome, you successfully signed in to our website!
    </Flex>
  )
});