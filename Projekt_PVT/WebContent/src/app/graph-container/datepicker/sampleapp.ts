import {Component, Output, EventEmitter} from '@angular/core';
import {MyDatePicker} from './mydatepicker';

@Component({
    selector: 'sample-date-picker',
    template: `<my-date-picker [options]="myDatePickerOptions" (dateChanged)="onDateChanged($event)" [selDate]="selectedDate"></my-date-picker>`,
    directives: [MyDatePicker]
})

export class SampleDatePicker {
    private myDatePickerOptions = {
        todayBtnTxt: 'Today',
        dateFormat: 'yyyy-mm-dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '34px',
        width: '200px'
    };
    
    selectedDate: string = '2016-05-13';
    @Output() date: EventEmitter<string> = new EventEmitter<string>();


    onDateChanged(event) {
        this.date.emit(event.formatted);
    }
}