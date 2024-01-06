import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefIconComponent } from './chef-icon.component';

describe('ChefIconComponent', () => {
  let component: ChefIconComponent;
  let fixture: ComponentFixture<ChefIconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChefIconComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChefIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
