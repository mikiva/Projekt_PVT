import {Component, Output, EventEmitter} from '@angular/core';


@Component({
    selector: 'choose-resolution',
    templateUrl: 'app/html/choose-resolution.html'



})

export class ChooseResolution {


    @Output() output: EventEmitter<string> = new EventEmitter<string>();
    resolution: string[] = ["day", "week", "month", "quarter", "year"];

    constructor() {

    }

    public onClick(value: string) {
        this.output.emit(value);
    }


}