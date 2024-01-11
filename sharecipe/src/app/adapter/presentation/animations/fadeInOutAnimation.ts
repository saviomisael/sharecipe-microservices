import {animate, state, style, transition, trigger} from "@angular/animations";

export const fadeInOutAnimation = trigger('fadeInOut', [
  state('void', style({opacity: 0})),
  transition(':enter, :leave', [
    animate(300, style({opacity: 1}))
  ])
])
