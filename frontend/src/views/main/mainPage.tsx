// @ts-nocheck
import React, {FC, useEffect} from 'react';
import {
  Box,
  Flex,
  Heading,
  Table,
  TableCaption,
  Text,
  Thead,
  VStack,
  Tr,
  Th,
  Tbody,
  Td,
  Grid,
  GridItem, Image
} from '@chakra-ui/react';
import {Link} from "react-router-dom";
import authStore from '../../store/auth';
import synagogueStore from '../../store/synagogue';
import {observer} from 'mobx-react-lite';
import {CardLink} from "./CardLink";
import booksBackground from "../../images/books.jpg";
import meetingBackground from "../../images/event.jpg";
import {Path} from '../../core/router/paths';


export const MainPage: FC = observer(() => {
  const {user} = authStore;
  const {isLoading, members, mySynagogue} = synagogueStore;

  useEffect(() => {
    synagogueStore.getMySynagogueInfo();
  }, [])

  console.log(user.name, 'name');
  // console.log(!isLoading && members, 'members')
  // console.log(!isLoading && mySynagogue, 'mySynagogue')

  return (
    <VStack fontSize="22px" color="gray.800" width="100%" padding={4} align="start" spacing={8}>
      <Box borderWidth='1px' borderRadius='lg' width="100%" padding={2}>
        <Heading as="h1" fontSize="1.75em">{mySynagogue?.name}</Heading>
        <VStack marginTop={8} align="start" spacing={10}>
          <Box width="100%">
            <Heading as="h2" fontSize="1.5em">О синагоге</Heading>
            <Text><Box as="strong">{mySynagogue?.tradition.name} традиция</Box> - именно к этой культурной общности
              евреев относится наша синагога и
              именно ею определяется весь стиль и вся атрибутика храма.<br/>
              Коротко о нашей традиции:<br/>
              {mySynagogue?.tradition.description}
            </Text>
            <Text as="strong" display="block" marginTop={2}>Некоторые характерные атрибуты для нашей общности:</Text>
            <Table width="100%" marginTop={2}>
              <TableCaption>Атрибуты традиции</TableCaption>
              <Thead>
                <Tr>
                  <Th>Название</Th>
                  <Th>Описание</Th>
                </Tr>
              </Thead>
              <Tbody>
                {// @ts-ignore
                  mySynagogue && mySynagogue.tradition.attributes.slice(0, 5).map((attr, i) => (
                    <Tr key={i}>
                      <Td>{// @ts-ignore
                        attr.name}</Td>
                      <Td>{// @ts-ignore
                        attr.description}</Td>
                    </Tr>
                  ))}
              </Tbody>
            </Table>
            <Text as="strong">О здании:</Text>
            <Text>
              Само здание имеет <Box as="i">{mySynagogue?.architectureStyle}</Box> архитектурный стиль. Площадь
              занимаемоей территории {mySynagogue?.size} м<sup>2</sup>
            </Text>
          </Box>
          <Box width="100%">
            <Heading as="h2" fontSize="1.5em">Просмотр помещений</Heading>
            <Table width="100%" marginTop={2}>
              <TableCaption>Помещения синагоги</TableCaption>
              <Thead>
                <Tr>
                  <Th>Помещение</Th>
                  <Th>Некоторые хранящиеся атрибуты</Th>
                </Tr>
              </Thead>
              <Tbody>
                {// @ts-ignore
                  mySynagogue && mySynagogue.premises.map((premise, index) => (
                    <Tr key={// @ts-ignore
                      premise.id}>
                      <Td>{// @ts-ignore
                        premise.name}</Td>
                      <Td>{synagogueStore.getCommaSeparatedPremises(index, 5)}</Td>
                    </Tr>
                  ))}
              </Tbody>
            </Table>
          </Box>
        </VStack>
      </Box>
      <Box width="100%">
        <Heading as="h1" fontSize="1.75em" textAlign="center">Функции синагоги</Heading>
        <Grid templateColumns="repeat(2, 1fr)" gap={2}>
          <GridItem>
            <Link to={Path.LIBRARY}><CardLink header="Библиотека"
                                              description="Здесь можно взять одну из многочисленных религиозных (и не только) книг"
                                              imageSrc={booksBackground}
            /></Link>
          </GridItem>
          <GridItem>
            <Link to={Path.EVENT}><CardLink header="События"
                                            description="Здесь просмотреть список мероприятий, запланировать свое или же забронировать место на собрание"
                                            imageSrc={meetingBackground}
            /></Link>
          </GridItem>
        </Grid>
      </Box>
    </VStack>
  )
});