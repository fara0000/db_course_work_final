import React, {FC, useEffect, useState} from 'react';
// @ts-ignore
import { Table } from 'antd';
import './table.css';
import {Button, useDisclosure} from "@chakra-ui/react";

const { Column } = Table;

export interface Props<T> {
    open: any
    data: Array<T>;
    columns: any;
    getSelectedTaskId?: any;
}

export const TableContent: FC<Props<object>> = (props) => {
    const {
        data,
        columns,
    } = props;

    const [renderColumns, setRenderColumns] = useState<any>([]);
    const { onOpen, isOpen, onClose } = useDisclosure();

    const handleTask = (row: any) => {
        console.log(row);
    }

    useEffect(() => {
        setRenderColumns(columns?.map((item: any) => (
          <Column
            key={item.key}
            title={item.title}
            dataIndex={item.dataIndex}
            onCellClick={(row) => {
                handleTask(row)
            }}
            // render={item.key === 'join' ? ((text: any) => (<Button>+</Button>)) : (<span>+</span>)}
          />
        )))
    }, [data]);

    return (
        <Table dataSource={data} style={{ width: '100%' }} size={'middle'}>
            {renderColumns}
        </Table>
    );
}