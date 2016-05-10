import {IDatasource} from './datasource.ts';

export interface DataSourceSingleJson{
    unit : string;
    name : string;
    data : IDatasource[];
}