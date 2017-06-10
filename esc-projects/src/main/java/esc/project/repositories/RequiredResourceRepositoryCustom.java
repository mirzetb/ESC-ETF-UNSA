package esc.project.repositories;

import java.util.List;

import esc.project.models.Project;
import esc.project.models.RequiredResource;

public interface RequiredResourceRepositoryCustom {

	public List<RequiredResource> getAllRRs(Project p);
}
