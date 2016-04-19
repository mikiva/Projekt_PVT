import {Injectable} from 'angular2/core';

@Injectable()

export class NumberService {
    getNumbers() : number[] {
        return [1, 2, 23 ,4, 156 ,6];
    }
}