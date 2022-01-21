import React, { FC, useEffect } from 'react';
import { TableContent } from './TableContent';
import {Flex, useDisclosure, Button} from '@chakra-ui/react';
import { Roles } from '../../utils/roles';
import { CreateEvent } from '../../modals/ReserveModal';
import { CreateMeeting } from '../../modals/CreateMeetingModal';

export interface Props {
    data: any;
    columns: any;
    current?: string;
    setData?: any;
}

export const TableBlock: FC<Props> = ({data, columns, current, setData}) => {
    const role = localStorage.getItem('role')
    const { onOpen, isOpen, onClose } = useDisclosure();

    useEffect(() => {
        data = data.map((item: any) => ({
            ...item,
            join: 'join',
        }))
        console.log(data, 'data');
    }, [data])

    return (
        <Flex w="100%" p="20px" flexDir="column">
            {current === 'meetings' &&
                <Button background="blue.500" color="#fff" w="120px" fontSize="15px" _hover={{ bg: 'blue.300' }} _focus={{ boxShadow: 'none'}} mb="12px" onClick={onOpen}>Create Meeting</Button>
            }
            {current === 'events' && role === Roles.общинник &&
                <Button background="blue.500" color="#fff" w="110px" fontSize="15px" _hover={{ bg: 'blue.300' }} _focus={{ boxShadow: 'none'}} mb="12px" onClick={onOpen}>Create Event</Button>
            }
            <TableContent
                open={onOpen}
                data={data}
                columns={columns}
                setData={setData}
            />
          {current === 'events' ?
            <CreateEvent isOpen={isOpen} onClose={onClose} /> : null
          }
          {current === 'meetings' ?
          <CreateMeeting isOpen={isOpen} onClose={onClose} /> : null
          }

        </Flex>
    )
}