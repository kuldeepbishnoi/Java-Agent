package com.casa.agent_service;

import com.casa.agent_service.agent.Agent;

public class AgentServiceApplication {

	public static void main(String[] args){
		Agent.premain("", null);
		System.out.println("AgentServiceApplication server is running...");
	}
}
