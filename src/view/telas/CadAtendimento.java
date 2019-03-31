package view.telas;

import control.AgendaC;
import control.AtendimentoC;
import control.UbsC;
import dao.PacienteDao;
import java.awt.Color;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.DefaultComboBoxModel;
import model.Agenda;
import model.Atendimento;
import model.Paciente;
import util.Yagami;
import view.popups.InsAgenda;
import view.popups.FindCid10;

/**
 * Tela de Cadastro de Atendimento
 *
 * @author Juan Galvão
 */
public class CadAtendimento extends javax.swing.JInternalFrame {

    protected FindCid10 ci = new FindCid10(null, true);

    // Variável para o contador
    protected Timer timer = new Timer();
    Agenda ag, agendaReal;
    DefaultComboBoxModel dlm = new DefaultComboBoxModel();
    List<Paciente> listPaciente;
    PacienteDao pdao = new PacienteDao();
    int idPaciente;

    /**
     * Creates new form CadAtendimento
     */
    public CadAtendimento() {
        initComponents();
        setLocation(100, 100);
        UbsC.CONTROL.listComboBox(cmbUbs);
        Yagami.YG.setPublicObject(new Agenda());

        idPaciente = Yagami.YG.getPublicId();
        //ativarCadastro(false);
        getPaciente();

        //verifica os registros no banco
        status();

        // Loop que atualzia a cada 50 milisegundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Garantir que o texto do campo endereço esteja correto
                //txtAgenda.setText("0");
                ag = (Agenda) Yagami.YG.getPublicObject();
                txtAgenda.setText(ag.getData_ag() + " - " + ag.getHora_ag());
                txtCid10.setText(String.valueOf(ci.idCid));

            }
        }, 0, 50);

    }

    private void getPaciente() {
        //String teste = "COMPONENTES...";
        List<Paciente> pac = pdao.listar(true, idPaciente);
        txtCodigo.setText(String.valueOf(pac.get(0).getId()));
        txtNome.setText(pac.get(0).getNome());
        txtConvenio.setText(pac.get(0).getConvenio());
        txtCns.setText(pac.get(0).getCns());
        txtObservacao.setText(pac.get(0).getObservacoes());

    }
    private int pAtendimento = 3; //id pre atendimento q é referente ao paciente substituir posteriormente o valor predefinido
    private int contPatend = 0; //quantidade de sessoes ja feitas pelo um unico paciente

    private void status() {
        //int num = 3; //id que sera do paciente
        String numFK = Integer.toString(pAtendimento);

        int statusAtendimento = 0;
        int tipoSessao = 0;
        //pesquisa com a  fk pre atendimento
        for (Atendimento stats : AtendimentoC.CONTROL.read(true, numFK)) {
            statusAtendimento = stats.getStatus();

        }
        //pesquisa com a  fk pre atendimento para verificar é maior q 1   "sessao <= 0 == consulta"
        for (Atendimento stats : AtendimentoC.CONTROL.read(true, "1")) {
            tipoSessao = stats.getStatus();
            System.out.println("status do di 1.......................:" + stats.getNum_sessao());
        }

        for (Atendimento atend : AtendimentoC.CONTROL.read(false, null)) {
            if (atend.getFk_patendimento() == pAtendimento) {
                contPatend++;
            }
        }

        if (tipoSessao != 0) {

            if (statusAtendimento == 1 || contPatend >= 10 /* ou se o paciente atual estiver finalizado */) {

                btnSalvar.setEnabled(false);
                btnSalvar.setBackground(Color.red);
                btnSalvar.setText("FINALIZADO");
                btnFinalizar.setVisible(false);
            }

        }

        System.out.println("quantidade de sessoes referente ao id do pré atedimento 3...:" + statusAtendimento);
        System.out.println("metodo iniciado");
        System.out.println("quantidade total de um unico paciente : " + contPatend);
        
         

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForm = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        cmbBloco = new javax.swing.JComboBox();
        txtConvenio = new javax.swing.JTextField();
        txtAgenda = new javax.swing.JTextField();
        cmbUbs = new javax.swing.JComboBox();
        cmbtpAtendimento = new javax.swing.JComboBox();
        lblProtocolo = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblConvenio = new javax.swing.JLabel();
        lblBloco = new javax.swing.JLabel();
        lblAgenda = new javax.swing.JLabel();
        lblUbs = new javax.swing.JLabel();
        lblTipoConsulta = new javax.swing.JLabel();
        txtCid10 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCns = new javax.swing.JFormattedTextField();
        btnSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        cmbConsulta = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        panelComandos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("Cadastro de Atendimento");
        setName("ATE000"); // NOI18N

        panelForm.setBackground(new java.awt.Color(245, 245, 245));
        panelForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNome.setEditable(false);
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtCodigo.setEditable(false);
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
        });

        cmbBloco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bloco 1", "Bloco 2", "Bloco 3" }));

        txtConvenio.setEditable(false);

        txtAgenda.setBackground(new java.awt.Color(204, 204, 255));
        txtAgenda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAgendaFocusGained(evt);
            }
        });
        txtAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgendaActionPerformed(evt);
            }
        });

        cmbUbs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UBS" }));

        cmbtpAtendimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta", "Sessão" }));
        cmbtpAtendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtpAtendimentoActionPerformed(evt);
            }
        });

        lblProtocolo.setText("Protocolo: 0");

        lblNome.setText("Nome:");

        lblCodigo.setText("Código:");

        lblConvenio.setText("Convênio:");

        lblBloco.setText("Bloco:");

        lblAgenda.setText("Agenda:");

        lblUbs.setText("UBS:");

        lblTipoConsulta.setText("Tipo de Atendimento:");

        txtCid10.setBackground(new java.awt.Color(204, 204, 255));
        txtCid10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCid10FocusGained(evt);
            }
        });
        txtCid10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCid10ActionPerformed(evt);
            }
        });

        jLabel4.setText("Cid10:");

        jLabel5.setText("CNS:");

        try {
            txtCns.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.####.####.####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCns.setVerifyInputWhenFocusTarget(false);
        txtCns.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCnsFocusGained(evt);
            }
        });
        txtCns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnsActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane1.setViewportView(txtObservacao);

        jLabel6.setText("Observação:");

        cmbConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ortopedia", "Cardiologia", "Pneumo", "Ginecologia" }));
        cmbConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbConsultaActionPerformed(evt);
            }
        });

        jLabel7.setText("Tipo de Consulta:");

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(lblProtocolo)
                        .addContainerGap())
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCid10)
                                .addComponent(jLabel4)
                                .addComponent(lblAgenda)
                                .addComponent(lblNome)
                                .addComponent(lblBloco)
                                .addComponent(txtNome)
                                .addComponent(cmbBloco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAgenda)
                                .addComponent(jLabel6)
                                .addComponent(jScrollPane1)
                                .addComponent(cmbConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7))
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipoConsulta)
                                    .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCns, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblCodigo)
                                                .addComponent(txtCodigo)
                                                .addComponent(txtConvenio)
                                                .addComponent(cmbUbs, 0, 190, Short.MAX_VALUE)
                                                .addComponent(lblUbs)
                                                .addComponent(lblConvenio))
                                            .addComponent(jLabel5))
                                        .addComponent(cmbtpAtendimento, javax.swing.GroupLayout.Alignment.LEADING, 0, 190, Short.MAX_VALUE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFormLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82))))))
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addComponent(lblProtocolo)
                .addGap(16, 16, 16)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConvenio)
                    .addComponent(lblBloco))
                .addGap(3, 3, 3)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBloco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgenda)
                    .addComponent(lblUbs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUbs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCns, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblTipoConsulta))
                .addGap(4, 4, 4)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCid10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbtpAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lblTitle.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTitle.setText("Cadastrar Atendimento");

        panelComandos.setBackground(new java.awt.Color(247, 247, 247));
        panelComandos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setText("Operações");

        jLabel2.setText("Sair");

        jLabel3.setText("ESC");

        javax.swing.GroupLayout panelComandosLayout = new javax.swing.GroupLayout(panelComandos);
        panelComandos.setLayout(panelComandosLayout);
        panelComandosLayout.setHorizontalGroup(
            panelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComandosLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelComandosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelComandosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        panelComandosLayout.setVerticalGroup(
            panelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComandosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(panelComandosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(258, Short.MAX_VALUE))
        );

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelComandos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFinalizar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lblTitle)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelComandos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizar)
                        .addGap(112, 112, 112))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void finaliza() {
        Atendimento att = new Atendimento();
        att.setFk_patendimento(pAtendimento);
        att.setStatus(1);
        AtendimentoC.CONTROL.update(att);
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        agendaReal = (Agenda) Yagami.YG.getPublicObject();
        Atendimento at = new Atendimento();
        Atendimento a = new Atendimento();
        at.setBloco_atual(cmbBloco.getSelectedItem().toString());
        at.setBloco_inicial(at.getBloco_atual());
        at.setData_at(ag.getData_ag());
        at.setHorario(ag.getHora_ag());
        at.setFk_medico(1); // TO DO
        at.setFk_patendimento(pAtendimento); // TO DO
        at.setFk_ubs(cmbUbs.getSelectedIndex() + 1);
        at.setObservacoes(txtObservacao.getText());
        at.setTipo_atendimento(cmbConsulta.getSelectedItem().toString());
        at.setObservacoes(txtObservacao.getText());
        at.setFk_cid10(ci.idCid);

        if (cmbtpAtendimento.getSelectedIndex() == 1) {

            if (contPatend > 1) {
                int soma = contPatend + 1;
                at.setNum_sessao(soma);
            } else {
                at.setNum_sessao(contPatend);
            }
        }

        //caso situacao for finalizado  ocultar botao salvar
        if (AtendimentoC.CONTROL.create(at)) {
            Agenda agendaObj = new Agenda();
            agendaObj.setData_ag(at.getData_at());
            agendaObj.setDetalhes("Má oie");
            agendaObj.setFk_atendimento(1);
            agendaObj.setHora_ag(at.getHorario());
            agendaObj.setTipo(at.getTipo_atendimento());
            if (AgendaC.CONTROL.create(agendaObj)) {
            } else {
                System.out.println("Vai tomar no cú!");
            }
        }

        if (contPatend >= 9 /*| statusAtendimento ==  ou se o paciente atual estiver finalizado */) {

            finaliza();
            btnSalvar.setEnabled(false);
            btnSalvar.setBackground(Color.red);
            btnSalvar.setText("FINALIZADO");
            btnFinalizar.setVisible(false);

           try {
                Thread.sleep(500);
                System.out.println("testando tenmpo fefewfewf");
                pAtendimento = 0;
                status();

            } catch (Exception e) {
            }

            
            
        }

        /*
         agendaReal = (Agenda) Yagami.YG.getPublicObject();
         Atendimento at = new Atendimento();
         at.setBloco_atual(cmbBloco.getSelectedItem().toString());
         at.setBloco_inicial(at.getBloco_atual());
         at.setData_at(ag.getData_ag());
         at.setHorario(ag.getHora_ag());
         at.setFk_medico(1); // TO DO
         //at.setFk_patendimento(pacObj.getId());
         //at.setFk_patendimento();
         //Integer.parseInt(txtCodigo.getText())
         at.setFk_ubs(cmbUbs.getSelectedIndex() + 1);
         at.setObservacoes("00");
         at.setTipo_atendimento(cmbConsulta.getSelectedItem().toString());
         AtendimentoC.CONTROL.create(at);

         Agenda agendaObj = new Agenda();
         agendaObj.setData_ag(at.getData_at());
         agendaObj.setDetalhes("Teste");
         agendaObj.setFk_atendimento(1);
         agendaObj.setFk_consulta(1);
         agendaObj.setHora_ag(at.getHorario());
         agendaObj.setTipo(at.getTipo_atendimento());
         AgendaC.CONTROL.create(ag);
         */
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtAgendaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAgendaFocusGained

        txtCodigo.requestFocus();
        new InsAgenda(null, true).setVisible(true);
        txtCodigo.requestFocus();
    }//GEN-LAST:event_txtAgendaFocusGained

    private void txtCid10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCid10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCid10ActionPerformed

    private void txtCid10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCid10FocusGained
        // TODO add your handling code here:
        // Requisita o foco no campo do Convenio
        txtConvenio.requestFocus();

        // Mostra popup de endereços
        ci.setVisible(true);

        // Pega a ID do endereço selecionado no popup
        txtCid10.setText(String.valueOf(ci.idCid));

        // Requisita o foco no campo do Convenio novamente (Garantia)
        txtConvenio.requestFocus();


    }//GEN-LAST:event_txtCid10FocusGained

    private void txtAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgendaActionPerformed

    private void cmbtpAtendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtpAtendimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbtpAtendimentoActionPerformed

    private void txtCnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnsActionPerformed

    private void cmbConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbConsultaActionPerformed

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCodigoFocusGained

    private void txtCnsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCnsFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCnsFocusGained

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        // TODO add your handling code here:

        finaliza();

    }//GEN-LAST:event_btnFinalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cmbBloco;
    private javax.swing.JComboBox cmbConsulta;
    private javax.swing.JComboBox cmbUbs;
    private javax.swing.JComboBox cmbtpAtendimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAgenda;
    private javax.swing.JLabel lblBloco;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblConvenio;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblProtocolo;
    private javax.swing.JLabel lblTipoConsulta;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUbs;
    private javax.swing.JPanel panelComandos;
    private javax.swing.JPanel panelForm;
    private javax.swing.JTextField txtAgenda;
    private javax.swing.JTextField txtCid10;
    private javax.swing.JFormattedTextField txtCns;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtConvenio;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables

}
