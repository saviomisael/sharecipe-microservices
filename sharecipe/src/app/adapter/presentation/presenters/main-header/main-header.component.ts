import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrl: './main-header.component.scss',
})
export class MainHeaderComponent {
  @Input() username!: string

  @Output() onLogout = new EventEmitter()

  handleLogoutClick() {
    this.onLogout.emit()
  }
}
