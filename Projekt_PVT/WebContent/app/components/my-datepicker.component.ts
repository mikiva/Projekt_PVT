import {bootstrap} from 'angular2/platform/browser';
import {Component, OnInit, Output, EventEmitter} from 'angular2/core';
import {MyDatePicker} from './mydatepicker';

@Component({
    selector: 'sample-date-picker',
    template: `<my-date-picker [options]="myDatePickerOptions" (dateChanged)="onDateChanged($event)" [selDate]="selectedDate"></my-date-picker>`,
    directives: [MyDatePicker]
})

export class SampleDatePicker implements OnInit {
    private myDatePickerOptions = {
        todayBtnTxt: 'Today',
        dateFormat: 'yyyy.mm.dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '34px',
        width: '260px'
    };
    
    @Output() output: EventEmitter<string> = new EventEmitter<string>();
    
    selectedDate: string = '2015.12.20';

    constructor() {}

    ngOnInit() {
        console.log('onInit(): SampleDatePicker')
    }
    
   
    onDateChanged(event) {
        console.log('onDateChanged(): ', event.date, ' - formatted: ', event.formatted, ' - epoc timestamp: ', event.epoc);
    }
    
    
}