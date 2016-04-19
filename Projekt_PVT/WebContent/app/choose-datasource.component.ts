import {Component} from 'angular2/core';
import {NumberService} from './test-numbers.service'

@Component({
    selector: 'choose-source',
    templateUrl: 'app/choose-datasource.html',
    providers: [NumberService]


})
export class ChooseSource {
private items : number[]
    constructor(nbr: NumberService) {
        this.items = nbr.getNumbers();
    }


}