import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardServicosComponent } from './card-servicos.component';

describe('CardServicosComponent', () => {
  let component: CardServicosComponent;
  let fixture: ComponentFixture<CardServicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardServicosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardServicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
