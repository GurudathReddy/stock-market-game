package com.vsm.stockmarket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vsm.stockmarket.achievements.AchievementFacade;
import com.vsm.stockmarket.constants.IApplicationConstants;
import com.vsm.stockmarket.domain.entity.LeagueMaster;
import com.vsm.stockmarket.domain.entity.UserMaster;
import com.vsm.stockmarket.exception.EmailNotFoundException;
import com.vsm.stockmarket.exception.LeagueException;
import com.vsm.stockmarket.exception.ServiceException;
import com.vsm.stockmarket.exception.service.UserServiceException;
import com.vsm.stockmarket.mbeans.FollowRequestFormBean;
import com.vsm.stockmarket.mbeans.LeaderBoardFormBean;
import com.vsm.stockmarket.mbeans.LeagueIdFormBean;
import com.vsm.stockmarket.mbeans.LeaguePlayerFormBean;
import com.vsm.stockmarket.mbeans.MyLeagueFormBean;
import com.vsm.stockmarket.platform.web.ResponseMessage;
import com.vsm.stockmarket.services.AlertsService;
import com.vsm.stockmarket.services.LeagueService;
import com.vsm.stockmarket.services.UserService;

/**
 * @author Gurudath Reddy
 * @version 1.0
 */
@Controller
public class LeagueController {

	private static final Log LOG = LogFactory
			.getLog(LeagueController.class);
	
	@Inject
	LeagueService leagueService;
	
	@Inject
	UserService userService;
	
	@Inject
	AlertsService alertService;

	@Inject
	private AchievementFacade achievementFacade;

	
	@RequestMapping(value = "/gameLeaguesList", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<MyLeagueFormBean> listAllUserGameLeagues() {
		try {
			return leagueService.getMyLeagues(userService.getLoggedInUser().getUserId());
		} catch (ServiceException e) {
			LOG.error(e);
			// TODO: HANDLE WHEN EXCEPTION
			return null;
		} catch (EmailNotFoundException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/leaguesUsersList", method = {
			RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<LeaguePlayerFormBean> getLeaguePlayers(
			@RequestParam("leagueId") long leagueId) {
		try {
			return leagueService.getLeaguePlayersBasedOnRanking(leagueId, -1);
		}
		catch (LeagueException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return null;
		} 
	}
	
	@RequestMapping(value = "/unlockLeague", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage unlockLeague(
			@RequestBody LeagueIdFormBean leagueIdFormBean) {
		
		try {
			leagueService.addUserToStarterLeague(userService.getLoggedInUser());
			return new ResponseMessage(ResponseMessage.Type.success,"You have successfully unlocked the Premier League");
		} catch (LeagueException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return new ResponseMessage(ResponseMessage.Type.error,"There was a technical error while processing your order. Please try again");
		} catch (EmailNotFoundException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return new ResponseMessage(ResponseMessage.Type.error,"There was a technical error while processing your order. Please try again");
		}
	}
	
	@RequestMapping(value = "/leaderBoard", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public List<LeaderBoardFormBean> getLeaderBoard() {
		
		try {
			return leagueService.getLeaderBoard();
		} catch (LeagueException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return null;
		}
	}
	
	@RequestMapping(value = "/followUser", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage followPlayer(
			@RequestBody FollowRequestFormBean followRequestFormBean) {
		try {
			UserMaster userMaster = userService.getLoggedInUser();
			UserMaster playerMaster = userService.findById(Long.valueOf(followRequestFormBean.getUserId()).longValue());
			if(userMaster.getUserId() == playerMaster.getUserId())
			{
				return new ResponseMessage(ResponseMessage.Type.error,"You can't follow yourself");
			}
			else
			{
				LeagueMaster leagueMaster = leagueService.getLeagueById(Long.valueOf(followRequestFormBean.getLeagueId()).longValue());
				if(!leagueService.checkFollowRecordExists(userMaster, playerMaster, leagueMaster))
				{
					leagueService.followPlayer(userMaster, playerMaster, leagueMaster);
					String message = "You have got a follow request from " + userMaster.getFirstName() + ". Please Accept or Reject the request in Followers Screen!"; 
					alertService.saveUserAlerts(playerMaster, message, IApplicationConstants.ALERT_TYPE_NOTIFICATIONS);
					// Evaluate achievements
					List<String> categories = new ArrayList<String>();
					categories.add("followers");
					achievementFacade.evaluate(playerMaster, categories);
					return new ResponseMessage(ResponseMessage.Type.success,"Follow request sent to the Player. You can follow him after he accepts your request!");
				}
				else
				{
					return new ResponseMessage(ResponseMessage.Type.warn,"You are already following this Player or Request is Pending Acceptance!");
				}
				
			}
		} catch (EmailNotFoundException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return new ResponseMessage(ResponseMessage.Type.error,"There was a technical error while processing your order. Please try again");
		} catch (UserServiceException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return new ResponseMessage(ResponseMessage.Type.error,"There was a technical error while processing your order. Please try again");
		} catch (NumberFormatException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return new ResponseMessage(ResponseMessage.Type.error,"There was a technical error while processing your order. Please try again");
		} catch (LeagueException e) {
			// TODO: HANDLE WHEN EXCEPTION
			LOG.error(e);
			return new ResponseMessage(ResponseMessage.Type.error,"There was a technical error while processing your order. Please try again");
		}
	}
}
