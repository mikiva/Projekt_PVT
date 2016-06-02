import {Component, Output, EventEmitter} from '@angular/core';
import {MyDatePicker} from './mydatepicker';

@Component({
    selector: 'main-date-picker',
    template: `<my-date-picker [options]="myDatePickerOptions" (dateChanged)="onDateChanged($event)" [selDate]="selectedDate"></my-date-picker>`,
    directives: [MyDatePicker]
})

export class MainDatePicker {
    private myDatePickerOptions = {
        todayBtnTxt: 'Today',
        dateFormat: 'yyyy-mm-dd',
        firstDayOfWeek: 'mo',
        sunHighlight: true,
        height: '34px',
        width: '200px'
    };
    
    selectedDate: string = '';
    @Output() date: EventEmitter<string> = new EventEmitter<string>();


    onDateChanged(event) {
        this.date.emit(event.formatted);
    }
}