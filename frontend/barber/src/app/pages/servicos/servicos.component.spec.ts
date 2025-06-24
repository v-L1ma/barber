import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicosComponent } from './servicos.component';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';

describe('ServicosComponent', () => {
  let component: ServicosComponent;
  let fixture: ComponentFixture<ServicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicosComponent, RouterTestingModule],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: {
                get: (key: string) => '0'
              }
            }
          }
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have two buttons with links to "lista" and "meus-agendamentos"', () => {
    const buttons = fixture.debugElement.queryAll(By.css('#buttons button'));
    expect(buttons.length).toBe(2);

    const routerLinks = buttons.map(btn => btn.attributes['ng-reflect-router-link']);
    expect(routerLinks).toContain('lista');
    expect(routerLinks).toContain('meus-agendamentos');
  });
});
