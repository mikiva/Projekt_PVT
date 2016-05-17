import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import {MyDatePicker} from './mydatepicker';

@Component({
    selector: 'sample-date-picker',
    template: `<my-date-picker [options]="myDatePickerOptions" (dateChanged)="onDateChanged($event)" [selDate]="selectedDate"></my-date-picker>`,
    directives: [MyDatePicker]
})

export class SampleDatePicker implements OnInit {
    private myDatePickerOptions = {
        todayBtnTxt: 'Today',
        dateFormat: 'yyyy-mm-dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '34px',
        width: '260px'
    };
    
    selectedDate: string = '2016-05-13';
    @Output() date: EventEmitter<string> = new EventEmitter<string>();

    constructor() {}

    ngOnInit() {
        console.log('onInit(): SampleDatePicker')
    }

    onDateChanged(event) {
        console.log(event.formatted);
        this.date.emit(event.formatted);
    }
}