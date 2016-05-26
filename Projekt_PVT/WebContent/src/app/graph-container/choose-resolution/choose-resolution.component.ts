import {Component, Output, EventEmitter} from '@angular/core';

@Component({
    selector: 'choose-resolution',
    templateUrl: 'src/app/graph-container/choose-resolution/choose-resolution.html'
})
export class ChooseResolution {
    @Output() output: EventEmitter<string> = new EventEmitter<string>();
    resolution: string[] = ["Day", "Week", "Month", "Quarter", "Year"];

   onClick(value: string) {
        this.output.emit(value);
    }
}