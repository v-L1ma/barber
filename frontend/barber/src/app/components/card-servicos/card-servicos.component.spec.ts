import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardServicosComponent } from './card-servicos.component';
import { By, Title } from '@angular/platform-browser';
import { AgendamentoComponent } from '../../pages/agendamento/agendamento.component';
import { RouterTestingModule } from '@angular/router/testing';
import { RouterLink, RouterLinkWithHref } from '@angular/router';

fdescribe('CardServicosComponent', () => {
  let component: CardServicosComponent;
  let fixture: ComponentFixture<CardServicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardServicosComponent, RouterTestingModule.withRoutes([
        { path: 'agendamento', component: AgendamentoComponent }
      ])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardServicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render text from params', ()=>{
    component.preco = "R$ 80,00";
    component.titulo = "Corte masculino";
    fixture.detectChanges();

    const h2 = fixture.debugElement.query(By.css(".card div h2")).nativeElement as HTMLHeadingElement;
    const p = fixture.debugElement.query(By.css(".card div p")).nativeElement as HTMLParagraphElement;

    expect(p.textContent).toContain("R$ 80,00");
    expect(h2.textContent).toContain("Corte masculino");
  });

});
