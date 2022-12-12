import React from 'react';
import ReactDOM from 'react-dom';
import { App } from './App';
import 'antd/dist/antd.css';
import './index.css';
import { ChakraProvider } from './core/chakra/chakraProvider';
// import chakraTheme from './core/chakra/chakraTheme';
// import { ChakraProvider } from '@chakra-ui/react';

ReactDOM.render(
  <React.StrictMode>
    <ChakraProvider>
        <App />
    </ChakraProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
