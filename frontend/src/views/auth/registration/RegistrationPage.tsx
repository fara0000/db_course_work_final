import React, { useEffect } from 'react';
import {
  Flex,
  Box,
  FormControl,
  FormLabel,
  Input,
  InputGroup,
  HStack,
  InputRightElement,
  Stack,
  Button,
  Heading,
  Text,
  useColorModeValue,
  chakra,
} from '@chakra-ui/react';
import { Path } from '../../../core/router/paths';
import { useState } from 'react';
import { Link as RouterLink } from 'react-router-dom';
import {  ViewIcon, ViewOffIcon } from '@chakra-ui/icons';
import { Form, Formik } from 'formik';
import { SelectField } from '../../../components/SelectField';
import { Roles } from '../../../utils/roles';
import { RegistrationFormValues } from './types';
import { TextInput } from '../../../components/TextInput';
import { getSynagoguesApi } from '../../../api/auth/api';

const options = [
  { value: 'chocolate', label: 'Chocolate' },
  { value: 'strawberry', label: 'Strawberry' },
  { value: 'vanilla', label: 'Vanilla' }
]

export const RegistrationPage = () => {
  const [synagogues, setSynagogues] = useState([]);
  const bg1 = useColorModeValue('gray.50', 'gray.800');
  const bg2 = useColorModeValue('white', 'gray.700');

  async function getData() {
    const res = await getSynagoguesApi();
    setSynagogues(res);
  }

  useEffect(() => {
    getData();
  }, [])

  return (
    <Formik<RegistrationFormValues>
      enableReinitialize
      initialValues={{
        firstName: '',
        lastName: '',
        login: '',
        password: '',
        role:'',
        synagogue: 0,
      }}
      onSubmit={(values) => console.log(values)}
    >
      {({ isSubmitting, dirty, isValid, values }) => (
      <Flex
        minH={'100vh'}
        align={'center'}
        justify={'center'}
        bg={bg1}
      >
        <Stack spacing={8} mx={'auto'} maxW={'lg'} pb="40px" px={6}>
          <Stack align={'center'} mb="8px">
            <Heading fontSize={'4xl'} textAlign={'center'}>
              Sign up
            </Heading>
          </Stack>
          <Form id="registration-page-form" style={{ margin: 0 }}>
            <Box
              rounded={'lg'}
              bg={bg2}
              boxShadow={'lg'}
              p="25px 26px"
            >
              <Stack spacing={4}>
                <HStack>
                  <Box>
                    <TextInput
                      isRequired
                      name='firstName'
                      label='First Name'
                    />
                  </Box>
                  <Box>
                   <TextInput
                    name='lastName'
                    label='Last Name'
                   />
                  </Box>
                </HStack>
                <TextInput
                  isRequired
                  name='login'
                  label='Login'
                />
                <TextInput
                  isRequired
                  name='password'
                  type='password'
                  label='Password'
                />
                  <SelectField
                    name='role'
                    label='Role'
                    isRequired
                    options={[
                      {
                        label: Roles.габай,
                        value: Roles.габай,
                      },
                      {
                        label: Roles.общинник,
                        value: Roles.общинник,
                      },
                    ]}
                  />
                <SelectField
                  name='synagogue'
                  label='Synagogue'
                  isRequired
                  options={synagogues?.map((item: any) => ({
                    id: item.id,
                    value: item.name,
                    label: item.name,
                  })) || []
                  }
                />
                <Stack spacing={10} pt={2}>
                  <Button
                    type="submit"
                    form="registration-page-form"
                    loadingText="Submitting"
                    size="lg"
                    bg={'blue.400'}
                    color={'white'}
                    _hover={{
                      bg: 'blue.500',
                    }}
                  >
                    Sign up
                  </Button>
                </Stack>
                <Stack>
                  <Text align={'center'}>
                    Already a user? <RouterLink to={Path.LOGIN}>
                    <chakra.span color='#4299E1' _hover={{ textDecoration: 'underline' }}>
                      Login
                    </chakra.span>
                  </RouterLink>
                  </Text>
                </Stack>
              </Stack>
            </Box>
          </Form>
        </Stack>
      </Flex>
      )}
    </Formik>
  );
}