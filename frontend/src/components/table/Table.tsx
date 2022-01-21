import {
  Box,
  BoxProps,
  Flex,
  HStack,
  Icon,
  Table,
  TableRowProps,
  Tbody,
  Td,
  Text,
  Th,
  Thead,
  Tr,
} from '@chakra-ui/react';
import React, { ReactNode, useEffect } from 'react';
import { VscTriangleDown, VscTriangleUp } from 'react-icons/vsc';
import {
  Column, ColumnInstance,
  useFilters,
  useGlobalFilter,
  useRowSelect,
  useSortBy,
  useTable,
} from 'react-table';
import { ActionsMenu } from './ui-components/actions-menu';
import { GlobalFilter } from './filter-components/global-search';
import { IndeterminateCheckbox } from './ui-components/checkbox';
import { fuzzyTextFilterFn } from './utils/fuzzy-search';

export interface TableProps<T> {
  columns: Column[]; // must be memoized with useMemo
  data: T[]; // must be memoized with useMemo
  actions?: (row: T) => {
    text: ReactNode;
    icon?: JSX.Element;
    onClick?: (value: T) => void;
  }[];
  searchBarColor?: BoxProps['bg'];
  stickyBar?: boolean;
  selectable?: 'checkbox' | 'click' | 'none';
  globalFilter?: boolean;
  size?: string;
  onRowsSelect?: (rows: T[]) => void;
  rowProps?: (row: T) => TableRowProps;
}

const filterTypes = {
  fuzzyText: fuzzyTextFilterFn,
};

export function CustomTable<T>({
  actions,
  stickyBar,
  selectable = 'checkbox',
  globalFilter = true,
  size = 'md',
  searchBarColor = 'transparent',
  data = [],
  columns = [],
  rowProps = () => ({}),
  onRowsSelect: setSelectedRows,
}: TableProps<T>) {
  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
    state,
    allColumns,
    setGlobalFilter,
    preGlobalFilteredRows,
  }: any = useTable(
    {
      columns,
      data,
      filterTypes,
    } as any,
    useFilters,
    useGlobalFilter,
    useSortBy,
    useRowSelect,
    (hooks) => {
      hooks.visibleColumns.push((columns:  any) => {
        const newColumns = [
          selectable === 'checkbox' && {
            id: 'selection',
            Header: ({ getToggleAllRowsSelectedProps }: any) => (
              <IndeterminateCheckbox {...getToggleAllRowsSelectedProps()} />
            ),
            Cell: ({ row }: any) => {
              return (
                <IndeterminateCheckbox {...row.getToggleRowSelectedProps()} />
              )
            },
          },
          ...columns,
          actions && {
            Header: () => null,
            id: 'actions',
            Cell: (original: any) => (
              <ActionsMenu
                styleConfig={{ opacity: 1 }}
                options={actions(original)
                  .filter(Boolean)
                  .map((a) => ({
                    ...a,
                    onClick: () => a.onClick?.(original),
                  }))}
              />
            ),
          },
        ].filter(Boolean);

        return newColumns;
      });
    }
  );

  useEffect(() => {
    if (setSelectedRows) {
      const selectedItems = Object.keys(state.selectedRowIds).map(
        (idx) => rows.find((r: any) => r.index === +idx)?.original
      );
      setSelectedRows(selectedItems);
    }
  }, [state.selectedRowIds]);

  return (
    <Box pos="relative" color="black">
      <Flex
        justify="space-between"
        align="center"
        px={searchBarColor !== 'transparent' ? '4' : ''}
        pos={stickyBar ? 'sticky' : 'static'}
        bg={searchBarColor}
      >
        {globalFilter && (
          <GlobalFilter
            preGlobalFilteredRows={preGlobalFilteredRows}
            globalFilter={state.globalFilter}
            setGlobalFilter={setGlobalFilter}
          />
        )}
        <HStack spacing={4}>
          {allColumns.map(
            (column: any) =>
              column.canFilter &&
              column.Filter && (
                <Flex key={column.id} align="center">
                  <Text
                    mr={2}
                    fontWeight="semibold"
                    fontSize="md"
                    color="gray.600"
                  >
                    {column.Header}:
                  </Text>
                  <Box flexBasis="180px">{column.render('Filter')}</Box>
                </Flex>
              )
          )}
        </HStack>
      </Flex>
      <Table {...getTableProps()} bg="white" size={size}>
        <Thead>
          {headerGroups.map((headerGroup: any) => (
            <Tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column: any) => (
                <Th
                  {...column.getHeaderProps(column.getSortByToggleProps())}
                  userSelect="none"
                  w={['selection', 'actions'].includes(column.id) ? 6 : ''}
                >
                  <Flex>
                    {column.render('Header')}
                    <Icon
                      as={
                        column.isSortedDesc
                          ? VscTriangleDown
                          : VscTriangleUp
                      }
                      color="blue.500"
                      boxSize="12px"
                      ml={1}
                      mb={1}
                      visibility={column.isSorted ? 'visible' : 'hidden'}
                      display={column.id === 'selection' ? 'none' : 'initial'}
                    />
                  </Flex>
                </Th>
              ))}
            </Tr>
          ))}
        </Thead>
        <Tbody {...getTableBodyProps()}>
          {rows.map((row: any) => {
            prepareRow(row);
            const customProps = rowProps(row.original) ?? {};
            return (
              <Tr
                {...row.getRowProps()}
                bg={row.isSelected ? 'blue.50' : customProps?.bg ?? ''}
                cursor={selectable === 'click' ? 'pointer' : ''}
                onClick={() => selectable === 'click' && row.toggleRowSelected()}
                {...customProps}
              >
                {row.cells.map((cell: any) => {
                  return <Td {...cell.getCellProps()}>{cell.render('Cell')}</Td>;
                })}
              </Tr>
            );
          })}
        </Tbody>
      </Table>
    </Box>
  );
}
