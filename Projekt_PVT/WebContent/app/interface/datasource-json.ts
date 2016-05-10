import {IDatasource} from './datasource.ts';

export interface DataSourceJson {
    xName: string;
    yName: string;
    xUnit: string;
    yUnit: string;
    data : IDatasource[];
}