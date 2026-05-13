// ========== SOZLAMALAR ==========
const API_BASE = 'http://localhost:8080';

// ========== NAVBAR ACTIVE LINK ==========
document.addEventListener('DOMContentLoaded', () => {
  const currentPage = window.location.pathname.split('/').pop() || 'index.html';
  const links = document.querySelectorAll('.navbar__links a');
  links.forEach(link => {
    const linkPage = link.getAttribute('href');
    if (linkPage === currentPage) {
      link.classList.add('active');
    }
  });

  // Burger menu
  const burger = document.querySelector('.navbar__burger');
  const navLinks = document.querySelector('.navbar__links');
  if (burger && navLinks) {
    burger.addEventListener('click', () => {
      navLinks.classList.toggle('open');
    });
  }
});

// ========== API HELPER ==========
async function apiPost(endpoint, data) {
  const response = await fetch(API_BASE + endpoint, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  });
  return response;
}

async function apiGet(endpoint) {
  const response = await fetch(API_BASE + endpoint);
  return response;
}

async function apiPut(endpoint) {
  const response = await fetch(API_BASE + endpoint, { method: 'PUT' });
  return response;
}

async function apiDelete(endpoint) {
  const response = await fetch(API_BASE + endpoint, { method: 'DELETE' });
  return response;
}

// ========== ALERT HELPER ==========
function showAlert(id, type, message) {
  const alert = document.getElementById(id);
  if (!alert) return;
  alert.className = `alert alert--${type} show`;
  alert.textContent = message;
  setTimeout(() => { alert.classList.remove('show'); }, 4000);
}

// ========== COUNTER ANIMATSIYA ==========
function animateCounter(el, target, duration = 1500) {
  let start = 0;
  const step = target / (duration / 16);
  const timer = setInterval(() => {
    start += step;
    if (start >= target) {
      el.textContent = target;
      clearInterval(timer);
    } else {
      el.textContent = Math.floor(start);
    }
  }, 16);
}

function initCounters() {
  const counters = document.querySelectorAll('[data-counter]');
  if (!counters.length) return;

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        const target = parseInt(entry.target.getAttribute('data-counter'));
        animateCounter(entry.target, target);
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.5 });

  counters.forEach(counter => observer.observe(counter));
}

document.addEventListener('DOMContentLoaded', initCounters);
