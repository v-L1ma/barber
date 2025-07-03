import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucessoAgendamentoComponent } from './sucesso-agendamento.component';
import { By } from '@angular/platform-browser';

describe('SucessoAgendamentoComponent', () => {
  let component: SucessoAgendamentoComponent;
  let fixture: ComponentFixture<SucessoAgendamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SucessoAgendamentoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SucessoAgendamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render text from param response message', ()=>{
    //arrange

    const expectedText = "Agendamento feito com sucesso!";
    component.responseMessage = expectedText;
    fixture.detectChanges();

    //act

    const paragraph = fixture.debugElement.query(By.css('#container h1'))
    .nativeElement as HTMLHeadingElement;

    //assert

    expect(paragraph.textContent).toBe(expectedText);
  });

  it("should render a SVG and a message of error when status is 'erro'",()=>{
    component.status = 'erro';
    const expectedMessage = 'Erro ao agendar.';
    component.responseMessage = expectedMessage;
    fixture.detectChanges();

    const svg = fixture.debugElement.query(By.css(".erro"));
    const responseMessage = fixture.debugElement.query(By.css("#container h1"))
    .nativeElement as HTMLHeadingElement;

    expect(svg).toBeTruthy();
    expect(responseMessage.textContent).toBe(expectedMessage);
  });

  it("should render a SVG and a message of sucess when status is 'sucesso'", ()=>{
    component.status = "sucesso";
    const expectedMessage = "Agendamento feito com sucesso!";
    component.responseMessage = expectedMessage;
    fixture.detectChanges();

    const svg = fixture.debugElement.query(By.css('.sucesso'));
    const h1 = fixture.debugElement.query(By.css('#container h1'))
    .nativeElement as HTMLHeadingElement;
    
    expect(svg).toBeTruthy();
    expect(h1.textContent).toBe(expectedMessage);
  });

  it("should call fecharPopUp() when click button 'fechar'",()=>{
    spyOn(component, 'fecharPopUp');
    fixture.detectChanges();

    const button = fixture.debugElement.query(By.css('button')).nativeElement as HTMLButtonElement;
    button.click();

    expect(component.fecharPopUp).toHaveBeenCalled();
  });
});
