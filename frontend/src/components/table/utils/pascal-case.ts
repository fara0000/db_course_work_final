import { startCase, toLower } from 'lodash';

export const pascalCase = (str?: string) => (str ? startCase(toLower(str)) : str);
