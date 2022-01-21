import React from "react";
import {
  Box,
  Stack,
  Heading,
  Flex,
  Text,
  Button,
  useDisclosure, Icon,
  chakra, MenuList, MenuItem, Menu, MenuButton,
} from '@chakra-ui/react';
import { HamburgerIcon } from "@chakra-ui/icons";
import { NavigationLink } from '../navigationLink/NavigationLink';
import { Path } from '../../core/router/paths';
import { observer } from 'mobx-react-lite';
import authStore from '../../store/auth';
import { FaUserAlt } from 'react-icons/fa';
import { useHistory } from 'react-router-dom';

export const PageHeader = observer(() => {
  const { user } = authStore;
  const name = localStorage.getItem('name')
  const role = localStorage.getItem('role')
  const history = useHistory();
  const { isOpen, onOpen, onClose } = useDisclosure();
  const handleToggle = () => (isOpen ? onClose() : onOpen());

  const logOut = () => {
    authStore.deleteTokenFromLocalStorage();
    history.push('login');
  }

  return (
    <Flex
      as="nav"
      align="center"
      justify="space-between"
      wrap="wrap"
      padding={3}
      pl={5}
      pr={5}
      pt={'10px'}
      bg="#1565c0"
      color="white"
    >
      <Flex align="center" mr={5}>
        <Heading as="h1" size="lg" color="#fff" letterSpacing={"tighter"}>
          Virtual Synagogue
        </Heading>
      </Flex>

      <Box display={{ base: "block", md: "none" }} onClick={handleToggle}>
        <HamburgerIcon />
      </Box>

      <Stack
        direction={{ base: "column", md: "row" }}
        display={{ base: isOpen ? "block" : "none", md: "flex" }}
        width={{ base: "full", md: "auto" }}
        alignItems="center"
        flexGrow={1}
        mt={{ base: 4, md: 0 }}
      >
        <NavigationLink to={Path.MAIN} text="Main" activeStyle={{ borderBottom: '1px solid #fff'}}/>
        <NavigationLink to={Path.LIBRARY} text="Library" activeStyle={{ borderBottom: '1px solid #fff'}}/>
        <NavigationLink to={Path.EVENT} text="Events" activeStyle={{ borderBottom: '1px solid #fff'}}/>
      </Stack>

        <Box
          display={{ base: isOpen ? "block" : "none", md: "block" }}
          mt={{ base: 4, md: 0 }}
          d={'flex'}
          flexDir="row"
        >
          <Box>
            Role: {role || user.role}
          </Box>
          <Button
            variant="outline"
            _hover={{ bg: "white.700"}}
            _focus={{ bg: "white.700" }}
            _active={{ bg: "#ccc" }}
            onClick={logOut}
          >
            <Icon as={FaUserAlt} mr="7px"/>
            <chakra.span fontSize="18px">{user.name || name}</chakra.span>
          </Button>
        </Box>
    </Flex>
)});