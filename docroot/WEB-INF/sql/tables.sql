create table expandotable (
	tableId LONG not null primary key,
	companyId LONG,
	classNameId LONG,
	name VARCHAR(75) null
);

create table opencps_domain_target (
	domainConfigId LONG not null primary key,
	targetDomain VARCHAR(75) null,
	userName VARCHAR(75) null,
	password_ VARCHAR(75) null
);

create table opencps_employee_jobpos (
	employeeId LONG not null,
	jobPosId LONG not null,
	primary key (employeeId, jobPosId)
);

create table opencps_fileentry_dossierFile (
	fileEntryId LONG not null,
	dossierFileId LONG not null,
	primary key (fileEntryId, dossierFileId)
);

create table opencps_workingunit_jobpos (
	jobPosId LONG not null,
	workingUnitId LONG not null,
	primary key (jobPosId, workingUnitId)
);

create table users_orgs (
	organizationId LONG not null,
	userId LONG not null,
	primary key (organizationId, userId)
);

create table users_roles (
	roleId LONG not null,
	userId LONG not null,
	primary key (roleId, userId)
);