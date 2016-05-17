import {Component, Output, OnChange, EventEmitter} from '@angular/core';
import {DatePicker} from 'ng2-datePicker;


@Component( {
    selector: "date-selector",
    templateUrl: "app/html/date-selector.html",
    directives: [NgIf, DatePicker, FORM_DIRECTIVES],
    bindings: [DatePicker]
})


export class DatePicker implements OnChange{
        
}