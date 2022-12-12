import React, { FC, useState } from 'react';
import {
  Flex,
  Box,
  Checkbox,
  Stack,
  Link,
  Button,
  Heading,
  Text,
  chakra,
} from '@chakra-ui/react';
import { Link as RouterLink, useHistory } from 'react-router-dom';
import { Path } from '../../../core/router/paths';
import { LoginFormInitialValues, UserType } from '../types';
import { Form, Formik, FormikHelpers } from 'formik';
import { TextInput } from '../../../components/input/TextInput';
import { useBackgroundColor } from '../utils';
import authStore from '../../../store/auth';
import { loginUserApi } from '../../../api/auth/api';
import { errorToast } from '../../../components/alerts/fail';
import { observer } from 'mobx-react-lite';

const wikiSynagogueUrl = 'https://en.wikipedia.org/wiki/Synagogue';

// TODO: use yup object for validate input because stars is not a good choice in Login Page

export const LoginPage: FC = observer(() => {
  const bg1 = useBackgroundColor('gray.50', 'gray.800');
  const bg2 = useBackgroundColor('white', 'gray.700');
  const history = useHistory();

  const signIn = (values: LoginFormInitialValues, helpers:  FormikHelpers<LoginFormInitialValues>) => {
    if(values.login && values.password) {
      loginUserApi(values).then((res) => {
        helpers.resetForm();
        if(res?.status === 200) {
          authStore.setUser(res.data.member);
          authStore.setTokenToLocalStorage(res.data.userToken, res.data.member);
          history.push('/main')
        } else {
          errorToast('Wrong user data, please try again!');
        }
      });
    } else {
      errorToast('Please, enter all needed fields!');
    }
  }

  return (
    <Formik<LoginFormInitialValues>
      enableReinitialize
      initialValues={{
        login: '',
        password: '',
      }}
      onSubmit={(values, formikHelpers) => {
        signIn(values, formikHelpers);
      }}
    >
      {({ isSubmitting, dirty, isValid, values }) => (
        <Flex
          bg={'#FFF'}
          minH={'100vh'}
          align={'center'}
          justify={'center'}
          userSelect={'none'}
        >
          <Stack spacing={8} mx={'auto'} maxW={'lg'} w="450px" pb="45px" px={6}>
            <Stack align={'center'}>
              <Heading fontSize={'4xl'}>Sign in to your account</Heading>
              <Text fontSize={'md'} color={'gray.600'} mt="0">
                to enjoy all of our Virtual <Link color={'blue.400'} href={wikiSynagogueUrl} target="_blank">Synagogue</Link> Platform
              </Text>
            </Stack>
            <Form id="login-page-form" style={{ marginTop: '26px' }}>
              <Box
                rounded={'lg'}
                bg={bg2}
                boxShadow={'lg'}
                p={8}>
                <Stack spacing={4}>
                  <TextInput
                    autoComplete="on"
                    name='login'
                    label='Login'
                  />
                  <TextInput
                    autoComplete="on"
                    name='password'
                    label='Password'
                  />
                  <Stack spacing={4}>
                    <Stack
                      direction={{ base: 'column', sm: 'row' }}
                      align={'start'}
                      justify={'space-between'}>
                      <Checkbox>Remember me</Checkbox>
                      <Link color={'blue.400'}>Forgot password?</Link>
                    </Stack>
                    <Button
                      type="submit"
                      form="login-page-form"
                      loadingText="Submitting"
                      size="lg"
                      bg={'blue.400'}
                      color={'white'}
                      _hover={{
                        bg: 'blue.500',
                      }}
                    >
                      Sign in
                    </Button>
                  </Stack>
                  <Stack>
                    <Text align={'center'}>
                      Don't have an account? <RouterLink to={Path.REGISTER}>
                      <chakra.span color='#4299E1' _hover={{ textDecoration: 'underline' }}>
                        Sing up
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
  )}
)
