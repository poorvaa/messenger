package org.poorva.java.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.poorva.java.messenger.database.DatabaseClass;
import org.poorva.java.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService()
	{
		profiles.put("Poorva",new Profile(1L,"poorva","Poorva","Gupta"));
		profiles.put("Samy",new Profile(2L,"gupta","Samy","Goel"));
	}
	
	public List<Profile> getAllProfiles()
	{
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName)
	{
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile)
	{
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile)
	{
		if(profile.getProfileName().isEmpty())
			return null;
		else
		{
			return profiles.put(profile.getProfileName(), profile);
		}
	}
	
	
	public Profile deleteProfile(String profileName)
	{
		return profiles.remove(profileName);
	}
}
