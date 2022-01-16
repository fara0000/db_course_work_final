import React, { FC } from 'react';

import { Box, ChakraProvider as ThemeProvider } from '@chakra-ui/react';
import chakraTheme from './chakraTheme';

export const ChakraProvider: FC = ({ children }) => (
  <ThemeProvider theme={chakraTheme}>
    <Box id="react-root-node">
      {children}
    </Box>
  </ThemeProvider>
);
