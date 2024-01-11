import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-alert-message',
  templateUrl: './alert-message.component.html',
  styleUrl: './alert-message.component.scss'
})
export class AlertMessageComponent {
  @Input() message!: string;
  @Input() type!: 'error' | 'success';
  @Output() onCloseClick = new EventEmitter();

  get alertMessageStyles() {
    return this.type === 'error' ? 'alert-message -error' : 'alert-message -success';
  }

  handleClick() {
    this.onCloseClick.emit();
  }
}
