import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-errors-list',
  templateUrl: './errors-list.component.html',
  styleUrl: './errors-list.component.scss',
})
export class ErrorsListComponent {
  @Input() errors!: string[];
}
