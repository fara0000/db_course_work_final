import {
  FormControl,
  FormControlProps,
  FormErrorMessage,
  FormLabel,
  TypographyProps,
} from '@chakra-ui/react';
import { FieldHookConfig, useField } from 'formik';
import React, { FC } from 'react';
import Select, { NamedProps } from 'react-select';

type SelectFieldProps = FieldHookConfig<string[] | string> &
  Partial<Pick<NamedProps, 'options'>> & {
  label?: string;
  fontSize?: TypographyProps['fontSize'];
  zIndex?: number;
  disableErrorMessage?: boolean;
  isMulti?: boolean;
  isClearable?: boolean;
  hideIfEmptyOptions?: boolean;
  renderOption?: (option: any) => JSX.Element;
} & Pick<FormControlProps, 'isRequired' | 'isDisabled'>;

export const SelectField: FC<SelectFieldProps> = ({
    options = [],
    label,
    placeholder,
    disableErrorMessage,
    isDisabled,
    isRequired,
    fontSize = 'md',
    zIndex,
    isClearable = true,
    hideIfEmptyOptions = false,
    isMulti = false,
    renderOption = (v) => v.label,
    ...props
                                                  }) => {
  const [field, meta, handlers] = useField(props);

  const onChange = (option: any) => {
    handlers.setValue(isMulti ? option?.map((item: { value: any; }) => item.value) : option?.value);
  };

  const getValue = () => {
    if (options) {
      return isMulti
        ? options.filter((option) => field.value.indexOf(option.value) >= 0)
        : options.find((option) => option.value === field.value);
    } else {
      return isMulti ? [] : ('' as any);
    }
  };

  const showSelect = () => {
    if (Array.isArray(options)) {
      return !!options.length;
    }

    return !!options;
  };

  if (!showSelect()) {
    return null;
  }

  // @ts-ignore
  // @ts-ignore
  return (
    <FormControl
      isInvalid={(meta.error && meta.touched) || undefined}
      minW="130px"
      {...{ isRequired, isDisabled }}
      zIndex={zIndex}
    >
      {label && (
        <FormLabel htmlFor={props.name} fontSize={fontSize}>
          {label}
        </FormLabel>
      )}

      <Select
        isSearchable
        isClearable={!isMulti}
        isMulti={isMulti}
        isDisabled={isDisabled}
        id={props.name}
        name={field.name}
        options={options}
        components={{ IndicatorSeparator: null }}
        formatOptionLabel={renderOption}
        placeholder={placeholder}
        value={getValue()}
        onBlur={() => handlers.setTouched(true)}
        onChange={onChange}
      />

      {!disableErrorMessage && <FormErrorMessage>{meta.error}</FormErrorMessage>}
    </FormControl>
  );
};
