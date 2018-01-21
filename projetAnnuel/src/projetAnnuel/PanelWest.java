package projetAnnuel;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

import org.json.JSONException;

public class PanelWest extends JPanel 
{
	 
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	private float fontSize;
	
	private ArrayList<JButton> listButton = new ArrayList<JButton>();
	
	protected PanelEast panelEast;
	
	public PanelWest(int wid,int hei, float fonts)
	{
		width=wid;
		height=hei;
		fontSize=fonts;
	}
	
	public void init(PanelEast aPanelEast)
    {
		panelEast = aPanelEast;
		String monImage = "img/blue_background_extra_long.jpg";
		ImagePanel panelDate = new ImagePanel(monImage,(12*width/100)-(2*height/100), 82*height/100);
		
		panelDate.setPreferredSize(new Dimension((12*width/100)-(2*height/100),82*height/100));
		
		//Ajout de ces derniers au panel nord
		this.add(panelDate);
		((FlowLayout)this.getLayout()).setVgap(height/100);
		((FlowLayout)this.getLayout()).setHgap(0);
		
		JButton timer = new JButton("Timer");
		timer.addActionListener(new TimerListener());
		
		JButton dashboard = new JButton("Dashboard");
		dashboard.addActionListener(new DashboardListener());
		
		JButton reports = new JButton("Reports");
		reports.addActionListener(new ReportsListener());
		
		JButton insights = new JButton("Insights");
		insights.addActionListener(new InsightsListener());
		
		JButton savedReports = new JButton("Saved Reports");
		savedReports.addActionListener(new SavedReportsListener());
		
		JButton projects = new JButton("Projects");
		projects.addActionListener(new ProjectsListener());
		
		JButton clients = new JButton("Clients");
		clients.addActionListener(new ClientsListener());
		
		JButton team = new JButton("Team");
		team.addActionListener(new TeamListener());
		
		JButton workspaces = new JButton("Workspaces");
		workspaces.addActionListener(new WorkspacesListener());
		
		listButton.add(timer);
		listButton.add(dashboard);
		listButton.add(reports);
		listButton.add(insights);
		listButton.add(savedReports);
		listButton.add(projects);
		listButton.add(clients);
		listButton.add(team);
		listButton.add(workspaces);
		
		for(JButton item : listButton)
		{
			item.setOpaque(false);
			item.setContentAreaFilled(false);
			item.setPreferredSize(new Dimension((12*width/100)-(4*height/100),7*height/100));
			item.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
			item.setFocusPainted(false);
			item.setMargin(new Insets(1,1,1,1));
			panelDate.add(item);
		}
		
		
	}
	

    class TimerListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayTimer();
        }
    }

    class DashboardListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayDashboard();
        }
    }

    class ReportsListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayReports();
        }
    }

    class InsightsListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayInsights();
        }
    }

    class SavedReportsListener implements ActionListener 
    {	
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displaySavedReports();
        }
    }

    class ProjectsListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	try {
				panelEast.displayProjects();
			} catch (IOException | JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    class ClientsListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayClients();
        }
    }

    class TeamListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayTeam();
        }
    }

    class WorkspacesListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        	panelEast.displayWorkspaces();
        }
    }
}
